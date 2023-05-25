package com.example.hranalytics.services.impl;

import com.example.hranalytics.domain.dtos.UserProjectAnalyticsDto;
import com.example.hranalytics.repositories.ProjectCameToRepository;
import com.example.hranalytics.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnalyticsServiceImpl implements AnalyticsService {
    private final ProjectCameToRepository projectCameToRepository;
    @Override
    public List<UserProjectAnalyticsDto> getProjectAnalytics(Integer projectId, String userId,
                                                             LocalDate from, LocalDate to) {
        var projectCameToList =
                projectCameToRepository.findByProject_Id(projectId)
                        .stream().filter(x -> x.getUserTechnologies().stream().anyMatch(y -> {
                            if (userId == null) return true;

                            return Objects.equals(y.getUser().getId(), userId);
                        })).toList();

        var response = projectCameToList.stream().map(
                x -> {

                }
        );
    }
}
