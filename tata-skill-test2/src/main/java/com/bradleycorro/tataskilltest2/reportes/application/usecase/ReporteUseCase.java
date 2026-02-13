package com.bradleycorro.tataskilltest2.reportes.application.usecase;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.out.IMovimientoRepositoryOut;
import com.bradleycorro.tataskilltest2.reportes.application.dto.ReporteMovimientoDTO;
import com.bradleycorro.tataskilltest2.reportes.domain.ports.in.IReporteUseCaseIn;
import com.bradleycorro.tataskilltest2.reportes.domain.ports.out.IClienteExternalRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteUseCase implements IReporteUseCaseIn {

    private final ICuentaRepositoryOut cuentaRepository;
    private final IMovimientoRepositoryOut movimientoRepository;
    private final IClienteExternalRepositoryOut clienteExternalRepository;

    /**
     * Genera un reporte detallado de estado de cuenta para un cliente en un rango de fechas.
     * Cruza la información de cuentas con sus respectivos movimientos realizados.
     * @param clienteId Identificador del cliente.
     * @param startDate Fecha inicial del rango.
     * @param endDate Fecha final del rango.
     * @return Lista de DTO con el detalle de cada transaccion y saldo acumulado.
     */
    @Override
    public List<ReporteMovimientoDTO> generarReporte(Long clienteId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<Movimiento> movimientos = movimientoRepository.findByClienteAndFechaRange(clienteId, startDate, endDate);
        String nombreCliente = clienteExternalRepository.findNombreByClienteId(clienteId).orElse(null);

        List<ReporteMovimientoDTO> resultado = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            double saldoInicial = cuenta.getSaldoInicial();
            for (Movimiento m : movimientos) {
                if (!cuenta.getId().equals(m.getCuentaId())) continue;
                ReporteMovimientoDTO dto = new ReporteMovimientoDTO();
                dto.setFecha(m.getFecha());
                dto.setClienteNombre(nombreCliente);
                dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                dto.setTipo(cuenta.getTipoCuenta());
                dto.setSaldoInicial(saldoInicial);
                dto.setEstado(cuenta.getEstado());
                dto.setMovimiento(m.getValor());
                dto.setSaldoDisponible(m.getSaldo());
                resultado.add(dto);
            }
        }
        return resultado;
    }
}
