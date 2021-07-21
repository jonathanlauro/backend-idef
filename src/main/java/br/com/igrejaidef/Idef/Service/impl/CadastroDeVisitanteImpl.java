package br.com.igrejaidef.Idef.Service.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeVisitante;
import br.com.igrejaidef.Idef.model.Body;
import br.com.igrejaidef.Idef.model.VisitanteModel;
import br.com.igrejaidef.Idef.repository.VisitanteRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CadastroDeVisitanteImpl implements CadastroDeVisitante {

    @Autowired
    VisitanteRepository repository;

    private static String ENDPOINT = "http://localhost:8000";

    @Override
    public VisitanteModel adicionar(VisitanteModel visitante) {
        validaVisitante(visitante);
        visitante.setDataVisita(new Date());
        return repository.save(visitante);
    }

    @Override
    public VisitanteModel atualizar(VisitanteModel visitante) {
        if(visitante.getId() == null){
            throw new ServiceException("Id não pode ser nulo");
        }
        validaVisitante(visitante);

        VisitanteModel atualizar = repository.findById((long)visitante.getId());
        if(atualizar == null) throw new NullPointerException("Usuario não encontrado");
        atualizar.setId(visitante.getId());
        atualizar.setNome(visitante.getNome());
        atualizar.setTelefone(visitante.getTelefone());
        atualizar.setEmail(visitante.getEmail());
        return repository.save(atualizar);
    }

    @Override
    public VisitanteModel remover(long id) {
        VisitanteModel deletar = repository.findById(id);
        if(deletar == null) throw new NullPointerException("Usuario não encontrado");
        repository.deleteById(id);
        return deletar;
    }

    @Override
    public List<VisitanteModel> listar() {
        return repository.findAll();
    }

    @Override
    public void enviarMsgDeAgradecimento() {

        String uri = ENDPOINT+"/send-message";
        RestTemplate restTemplate = new RestTemplate();
        List<VisitanteModel> visitanteLista = repository.findAll();
        for(VisitanteModel visitante : visitanteLista){
            Body corpo = new Body(visitante.getTelefone(), "Obrigado por comparecer ao culto "+ visitante.getNome());
            restTemplate.postForObject(uri,corpo,Object.class);
        }

    }

    private void validaVisitante(VisitanteModel visitante) throws ServiceException{
        List<String> erro = new ArrayList<>();

        if(visitante.getNome() == null || visitante.getNome().isEmpty()){
            erro.add("nome nao pode ser vazio");
        }
        if(visitante.getTelefone() == null || visitante.getTelefone().length() != 12){
            erro.add("telefone invalido!");
        }
        if(visitante.getEmail() == null || visitante.getEmail().isEmpty()){
            erro.add("email inválido!");
        }
        if(erro.size() > 0){
            throw new ServiceException("verifique todos os campos!");
        }
    }

}
