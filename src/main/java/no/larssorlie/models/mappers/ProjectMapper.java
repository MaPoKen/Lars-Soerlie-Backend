package no.larssorlie.models.mappers;

import no.larssorlie.models.domain.Project;
import no.larssorlie.models.dto.NewProjectDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.models.dto.SkillDTO;

import java.util.stream.Collectors;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO().toBuilder().id(project.getId()).urls(project.getUrls()).title(project.getTitle()).description(project.getDescription()).skills(project.getSkills().stream().map(SkillDTO::toDto).collect(Collectors.toSet())).build();
    }

    public static Project toModel(ProjectDTO project) {
        return new Project().toBuilder().id(project.getId()).urls(project.getUrls()).title(project.getTitle()).description(project.getDescription()).skills(project.getSkills().stream().map(SkillDTO::toModel).collect(Collectors.toSet())).build();
    }

    public static Project toModel(NewProjectDTO project) {
        return new Project().toBuilder().urls(project.getUrls()).title(project.getTitle()).description(project.getDescription()).skills(project.getSkills().stream().map(SkillDTO::toModel).collect(Collectors.toSet())).build();
    }
}
