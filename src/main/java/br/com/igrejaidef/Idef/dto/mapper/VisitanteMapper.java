package br.com.igrejaidef.Idef.dto.mapper;

import br.com.igrejaidef.Idef.dto.modelo.Visitante;
import br.com.igrejaidef.Idef.model.VisitanteModel;
import br.com.igrejaidef.Idef.utils.UtilitarioDeConversao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class VisitanteMapper implements Mapper<VisitanteModel, Visitante>{

    @Autowired
    UtilitarioDeConversao conversor;

    @Override
    public VisitanteModel toEntidade(Visitante modelo) throws Exception {
        return conversor.converter(modelo,this::fromDto);
    }

    @Override
    public Visitante toModelo(VisitanteModel entidade) throws Exception {
        return conversor.converter(entidade,this::fromModel);
    }

    @Override
    public List<VisitanteModel> toListaDeEntidades(Iterable<Visitante> modelos) throws Exception {
        return conversor.converterColecao(modelos,this::fromDto);
    }

    @Override
    public List<Visitante> toListaDeModelos(Iterable<VisitanteModel> entidades) throws Exception {
        return conversor.converterColecao(entidades,this::fromModel);
    }

    private Visitante fromModel(VisitanteModel model){
        return new Visitante(
                model.getId(),
                model.getNome(),
                model.getTelefone(),
                model.getEmail(),
                converterDataParaBr(model.getDataVisita())
        );
    }
    private VisitanteModel fromDto(Visitante dto) throws ParseException {
        return new VisitanteModel(
                dto.getId(),
                dto.getNome(),
                dto.getTelefone(),
                dto.getEmail(),
                converterStringParaData(dto.getDataVisita())
        );
    }
    private String converterDataParaBr(Date data){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(data);
        return dataFormatada;
    }
    private Date converterStringParaData(String data) throws ParseException {
        if(data == null){
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = formato.parse(data);
        return dataFormatada;
    }
}
