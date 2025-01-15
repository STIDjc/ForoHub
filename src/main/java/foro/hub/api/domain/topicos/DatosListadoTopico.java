package foro.hub.api.domain.topicos;

public record DatosListadoTopico(

        String titulo,
        String mensaje,
        String fechaDeCreacion,
        String autor,
        String curso,
        String status
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaDeCreacion(),
            topico.getAutor(),
            topico.getCurso(),
            topico.getStatus());
    }
}