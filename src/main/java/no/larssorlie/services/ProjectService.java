package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.NewProjectDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.repositories.ProjectRepository;
import reactor.core.publisher.Mono;

@Singleton
public class ProjectService {
  @Inject
  private ProjectRepository projectRepository;

  public Mono<List<ProjectDTO>> getAllProjects() {
    return projectRepository.findAll().map(ProjectDTO::toDTO).collectList();
  }

  public Mono<ProjectDTO> getProject(Long id) {
    return projectRepository.findById(id).map(ProjectDTO::toDTO);
  }

  public Mono<ProjectDTO> createProject(NewProjectDTO newProject) {
    return projectRepository.save(newProject.toModel()).map(ProjectDTO::toDTO);
  }

  public Mono<Long> deleteProject(Long id) {
    return projectRepository.deleteById(id);
  }

  public Mono<ProjectDTO> updateProject(Long id, NewProjectDTO newProject) {
    return projectRepository
      .update(newProject.toModel(id))
      .map(ProjectDTO::toDTO);
  }
}
