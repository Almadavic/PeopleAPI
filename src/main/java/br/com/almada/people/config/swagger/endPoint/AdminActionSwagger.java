package br.com.almada.people.config.swagger.endPoint;

import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import br.com.almada.people.dto.response.StatusResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "AdminAction", description = "Operações de gerenciamento permitidas apenas ao administrador")
public interface AdminActionSwagger {

    @Operation(summary = "Realiza o backup de dados no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Backup realizado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Senha inválida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<StatusResponseDTO> backup(@Parameter(name = "password") String password);

}
