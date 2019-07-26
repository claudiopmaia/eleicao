package com.eleicao.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.eleicao.web.dao.CandidatoDAO;
import com.eleicao.web.modelo.Candidato;
import com.eleicao.web.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Candidato.class)
public class CandidatoConverter implements Converter {
    
    @Inject
    private CandidatoDAO candidatoDAO;
    
    public CandidatoConverter() {
        this.candidatoDAO = CDIServiceLocator.getBean(CandidatoDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Candidato retorno = null;
        
        if (value != null) {
            retorno = this.candidatoDAO.buscarPeloCodigo(new Long(value));
        }
        
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long codigo = ((Candidato) value).getCodigo();
            String retorno = (codigo == null? null: codigo.toString());
            
            return retorno;
        }
        return "";
    }

}

