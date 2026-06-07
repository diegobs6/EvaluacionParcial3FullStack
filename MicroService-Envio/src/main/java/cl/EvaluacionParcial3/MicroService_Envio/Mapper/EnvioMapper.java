package cl.EvaluacionParcial3.MicroService_Envio.Mapper;

import cl.EvaluacionParcial3.MicroService_Envio.Dto.EnvioRequest;
import cl.EvaluacionParcial3.MicroService_Envio.Dto.EnvioResponse;
import cl.EvaluacionParcial3.MicroService_Envio.Model.Envio;
import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    public Envio fromRequest(EnvioRequest request){
        return Envio.builder()
                .pedidoId(request.getPedidoId())
                .usuarioId(request.getUsuarioId())
                .direccionDestino(request.getDireccionDestino())
                .estado(request.getEstado())
                .fechaDespacho(request.getFechaDespacho())
                .fechaEstimadaEntrega(request.getFechaEstimadaEntrega())
                .empresaEnvio(request.getEmpresaEnvio())
                .numeroSeguimiento(request.getNumeroSeguimiento())
                .build();
    }

    public EnvioResponse toResponse(Envio envio){
        return EnvioResponse.builder()
                .id(envio.getId())
                .pedidoId(envio.getPedidoId())
                .usuarioId(envio.getUsuarioId())
                .direccionDestino(envio.getDireccionDestino())
                .estado(envio.getEstado())
                .fechaDespacho(envio.getFechaDespacho())
                .fechaEstimadaEntrega(envio.getFechaEstimadaEntrega())
                .empresaEnvio(envio.getEmpresaEnvio())
                .numeroSeguimiento(envio.getNumeroSeguimiento())
                .build();
    }
}