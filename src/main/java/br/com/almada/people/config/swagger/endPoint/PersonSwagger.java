package br.com.almada.people.config.swagger.endPoint;

import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.StatusDataCollectionLinksResponseDTO;
import br.com.almada.people.dto.response.StatusDataResponseDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.dto.response.StatusResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "People", description = "Operações relacionadas á Pessoas")
public interface PersonSwagger {

    @Operation(summary = "Encontra uma lista de Pessoas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas com sucesso | Nenhum dado encontrado no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))})
    })
    ResponseEntity<StatusDataCollectionLinksResponseDTO<List<PersonResponseDTO>>> findAll(String name) throws InterruptedException;

    @Operation(summary = "Encontra uma Pessoa por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> findById(@Parameter(name = "personId") String personId);

    @Operation(summary = "Insere uma nova Pessoa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erros de validação de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> save(PersonRequestDTO personRequestDTO, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Atualiza uma nova Pessoa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erros de validação de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> update(@PathVariable(value = "personId") String personId, PersonRequestDTO personRequestDTO);

    @Operation(summary = "Deleta uma Pessoa por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StatusDataResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada no banco de dados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<StatusResponseDTO> remove(@PathVariable(value = "personId") String personId);
}
