package br.com.igrejaidef.Idef.Service.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeVisitante;
import br.com.igrejaidef.Idef.model.Body;
import br.com.igrejaidef.Idef.model.VisitanteModel;
import br.com.igrejaidef.Idef.model.VisitantePessoa;
import br.com.igrejaidef.Idef.repository.VisitanteRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public void enviarMsgDeAgradecimento(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date dataFormatada = formato.parse(data);

        Date amanha = new Date(dataFormatada.getTime() + (1000 * 60 * 60 * 24));

        String uri = ENDPOINT+"/send-message";
        RestTemplate restTemplate = new RestTemplate();
        List<VisitanteModel> busca = repository.findAll();
        List<VisitanteModel> visitantesDoDia = new ArrayList<>();
        for(VisitanteModel visitantes:busca){
            if(visitantes.getDataVisita().after(dataFormatada) && visitantes.getDataVisita().before(amanha)){
                visitantesDoDia.add(visitantes);
            }
        }
        for(VisitanteModel visitante : visitantesDoDia){
            Body corpo = new Body("55"+visitante.getTelefone(), "Oi "+ visitante.getNome()+
                    " obrigado por comparecer ao culto, estamos em oração por você,"+
                    " tem algum pedido específico? Nos mande uma menssagem aqui mesmo e vamos avisar a nossa equipe. "+
                    "Deus Abençoe sua semana. ");
            restTemplate.postForObject(uri,corpo,Object.class);
        }

    }
    @Override
    public void enviarMsgDeLembrete(String msg) throws ParseException {
        String uri = ENDPOINT+"/send-message";
        RestTemplate restTemplate = new RestTemplate();
        List<VisitantePessoa> visitantePessoas = repository.listarDistict();

        for(VisitantePessoa visitante : visitantePessoas){
            Body corpo = new Body("55"+visitante.getTelefone(), "Oi "+ visitante.getNome()+
                    " tudo bem ? "+msg);
            restTemplate.postForObject(uri,corpo,Object.class);
        }

    }
    @Override
    public void enviarMsgDeAusencia(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date dataFormatada = formato.parse(data);

        String uri = ENDPOINT+"/send-message";
        RestTemplate restTemplate = new RestTemplate();
        List<VisitanteModel> busca = repository.findAll();
        List<VisitanteModel> visitantesDoDia = new ArrayList<>();
        for(VisitanteModel visitantes:busca){
            if(visitantes.getDataVisita().after(dataFormatada)){
                visitantesDoDia.add(visitantes);
            }
        }
        for(VisitanteModel visitante : visitantesDoDia){
            Body corpo = new Body("55"+visitante.getTelefone(), "Oi "+ visitante.getNome()+
                    ", está tudo bem com você ? Sentimos sua falta no culto..."+
                    " Se precisar de algo, saiba que estamos sempre aqui. "+
                    "Deus Abençoe sua semana. ");
            restTemplate.postForObject(uri,corpo,Object.class);
        }

    }

    private void validaVisitante(VisitanteModel visitante) throws ServiceException{
        List<String> erro = new ArrayList<>();

        if(visitante.getNome() == null || visitante.getNome().isEmpty()){
            erro.add("nome nao pode ser vazio");
        }
        if(visitante.getTelefone() == null || visitante.getTelefone().length() != 10){
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
