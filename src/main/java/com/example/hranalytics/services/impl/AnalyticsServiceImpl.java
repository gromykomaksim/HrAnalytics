package com.example.hranalytics.services.impl;

import com.example.hranalytics.domain.dtos.EvenTypeInfoDto;
import com.example.hranalytics.domain.dtos.ProjectAnalyticsDto;
import com.example.hranalytics.domain.dtos.UserAnalyticsDto;
import com.example.hranalytics.domain.enums.EventTypeEnum;
import com.example.hranalytics.domain.model.Project;
import com.example.hranalytics.domain.model.ProjectCameTo;
import com.example.hranalytics.repositories.ProjectCameToRepository;
import com.example.hranalytics.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnalyticsServiceImpl implements AnalyticsService {
    private final ProjectCameToRepository projectCameToRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ProjectAnalyticsDto> getProjectAnalytics(Integer projectId, String userId,
                                                         LocalDate from, LocalDate to,
                                                         Integer deleted, Integer approved) {
        var projectCameToList =
                projectCameToRepository.findAll().stream()
                        .filter(x -> projectId == null || projectId.equals(x.getProject().getId()))
                        .filter(x -> x.getUserTechnologies().stream()
                                .anyMatch(y -> userId == null || userId.equals(y.getUser().getId())))
                        .filter(x -> deleted == null || x.getDeleted() == deleted).toList();

        Map<Project, List<UserAnalyticsDto>> projectToUser = new HashMap<>();

        projectCameToList.forEach(
                projectCameTo -> {
                    var userProjectAnalytics = initUserProjectAnalytics(projectCameTo);
                    var fromDate = initFromDate(projectCameTo, from);
                    var toDate = initToDate(projectCameTo, to);

                    while (!fromDate.isAfter(toDate)) {
                        var workedHours = new AtomicInteger(projectCameTo.getRate());
                        var fromDateForLambda = fromDate;

                        if (fromDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                                || fromDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                            workedHours.set(0);

                        projectCameTo.getEmsEvents().stream()
                                .filter(x -> approved == null || x.getApprove().ordinal() == approved)
                                .filter(x -> x.getFrom().toLocalDate().equals(fromDateForLambda))
                                .forEach(emsEvent -> {
                                    switch (emsEvent.getEventType()) {
                                        case SICK, SOCIAL_VACATION, PAID_VACATION -> {
                                            workedHours.set(0);

                                            if (fromDateForLambda.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                                                    || fromDateForLambda.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                                                return;
                                        }
                                    }
                                    var eventMinutes = userProjectAnalytics.getEvenTypeInfoList()
                                            .get(emsEvent.getEventType().ordinal()).getMinutes()
                                            + emsEvent.getMinutes();

                                    userProjectAnalytics.getEvenTypeInfoList().get(emsEvent.getEventType().ordinal()).setMinutes(eventMinutes);
                                }
                        );
                        var totalWorkedHours = userProjectAnalytics.getEvenTypeInfoList()
                                .get(EventTypeEnum.WORK.ordinal()).getHours()
                                + workedHours.get();

                        userProjectAnalytics.getEvenTypeInfoList().get(EventTypeEnum.WORK.ordinal())
                                .setHours(totalWorkedHours);

                        fromDate = fromDate.plusDays(1);
                    }
                    userProjectAnalytics.getEvenTypeInfoList().forEach(
                            evenTypeInfo -> {
                                evenTypeInfo.setHours(evenTypeInfo.getHours() + evenTypeInfo.getMinutes() / 60);
                                evenTypeInfo.setMinutes(evenTypeInfo.getMinutes() % 60);
                            }
                    );
                    if (!projectToUser.containsKey(projectCameTo.getProject()))
                        projectToUser.put(projectCameTo.getProject(), new ArrayList<>());

                    var list = projectToUser.get(projectCameTo.getProject());
                    list.add(userProjectAnalytics);
                }
        );

        List<ProjectAnalyticsDto> response = new ArrayList<>();

        projectToUser.forEach(
                (x, y) -> {
                    var object = new ProjectAnalyticsDto();

                    object.setId(x.getId());
                    object.setName(x.getName());
                    object.setUserProjectAnalyticsList(y);

                    response.add(object);
                }
        );

        return response;
    }

    private UserAnalyticsDto initUserProjectAnalytics(ProjectCameTo projectCameTo) {
        var userProjectAnalytics = new UserAnalyticsDto();
        var user = projectCameTo.getUserTechnologies().stream().toList().get(0).getUser();

        userProjectAnalytics.setUserId(user.getId());
        userProjectAnalytics.setFirstName(user.getFirstName());
        userProjectAnalytics.setLastName(user.getLastName());
        userProjectAnalytics.setTechnologies(
                projectCameTo.getUserTechnologies().stream()
                        .map(x -> x.getTechnology().getName()).collect(Collectors.toList()));
        userProjectAnalytics.setEvenTypeInfoList(Arrays.stream(EventTypeEnum.values())
                .map(eventType -> new EvenTypeInfoDto(eventType.ordinal())).collect(Collectors.toList()));

        return userProjectAnalytics;
    }

    private LocalDate initFromDate(ProjectCameTo projectCameTo, LocalDate from) {
        if (from == null || from.isBefore(projectCameTo.getFrom().toLocalDate()))
            return projectCameTo.getFrom().toLocalDate();

        return from;
    }

    private LocalDate initToDate(ProjectCameTo projectCameTo, LocalDate to) {
        var toDate = to;

        if (projectCameTo.getTo() != null
                && (toDate == null || projectCameTo.getTo().toLocalDate().isBefore(toDate)))
            toDate = projectCameTo.getTo().toLocalDate();

        if (toDate == null || LocalDate.now().isBefore(toDate))
            toDate = LocalDate.now();

        return toDate;
    }
}
