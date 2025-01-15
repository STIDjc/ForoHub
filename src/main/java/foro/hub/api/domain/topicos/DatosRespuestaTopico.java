package foro.hub.api.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
