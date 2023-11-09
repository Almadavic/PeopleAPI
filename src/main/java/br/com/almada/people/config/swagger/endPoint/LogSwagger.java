package br.com.almada.people.config.swagger.endPoint;

import br.com.almada.people.dto.response.StatusDataCollectionLinksResponseDTO;
import br.com.almada.people.dto.response.StatusDataResponseDTO;
import br.com.almada.people.dto.response.LogResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Logs", description = "Operações relacionadas á logs")
public interface LogSwagger {

    @Operation(summary = "Encontra uma lista de Logs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs encontrados com sucesso | Nenhum dado encontrado no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))})
    })
    ResponseEntity<StatusDataCollectionLinksResponseDTO<List<LogResponseDTO>>> findAll();

}
