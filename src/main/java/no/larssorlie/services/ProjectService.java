package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.NewProjectDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.models.mappers.ProjectMapper;
import no.larssorlie.repositories.ProjectRepository;
import reactor.core.publisher.Mono;

@Singleton
public class ProjectService {
  @Inject
  private ProjectRepository projectRepository;

  public Mono<List<ProjectDTO>> getAllProjects() {
    return projectRepository.findAll().map(ProjectMapper::toDTO).collectList();
  }

  public Mono<ProjectDTO> getProject(Long id) {
    return projectRepository.findById(id).map(ProjectMapper::toDTO);
  }

  public Mono<ProjectDTO> createProject(NewProjectDTO newProject) {
    return projectRepository.save(ProjectMapper.toModel(newProject)).map(ProjectMapper::toDTO);
  }

  public Mono<Long> deleteProject(Long id) {
    return projectRepository.deleteById(id);
  }

  public Mono<ProjectDTO> updateProject(Long id, NewProjectDTO newProject) {
    return projectRepository
      .update(ProjectMapper.toModel(newProject,id))
      .map(ProjectMapper::toDTO);
  }
}
