package com.marllon.vieira.vergili.sistema_gestao_projetos.Services.Project;

/**ProjectService
 Crie a interface ProjectService com métodos: create recebendo ProjectRequest
 e userId do criador, findById, findAll com paginação,
 findByUserId retornando projetos que o usuário participa ou criou,
 update, delete, addMember recebendo projectId e userId, removeMember,
 e getProjectStats retornando estatísticas. A implementação deve validar
 se os membros existem ao criar projeto, verificar permissões
 (apenas criador ou admins podem deletar),
 e calcular totalTasks e completedTasks ao retornar ProjectResponse.*/
public interface ProjectService {



}
