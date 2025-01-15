package foro.hub.api.controller;

import foro.hub.api.domain.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaTopico(topico));
    }
    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listarTopicos(){
        var topicos = topicoRepository.findAll().stream()
                .map(DatosListadoTopico::new).toList();
        return ResponseEntity.ok(topicos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable long id) {
        var topicoPorid = topicoRepository.findById(id);
        if (topicoPorid.isPresent()) {
            return ResponseEntity.ok(new DatosListadoTopico(topicoPorid.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                           @PathVariable long id) {
        var topicoActualizar = topicoRepository.getReferenceById(id);
        topicoActualizar.actualizarTopico(datosRegistroTopico);
        return ResponseEntity.ok( new DatosRespuestaTopico(topicoActualizar));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable long id) {
        var topicoAEiminar = topicoRepository.findById(id);
        if (topicoAEiminar.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
