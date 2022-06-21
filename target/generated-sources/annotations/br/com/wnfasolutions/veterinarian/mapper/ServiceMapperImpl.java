package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO.ServiceResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO.ServiceDOBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:33:47-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDO toModel(ServiceRequestDTO serviceRequestDTO) {
        if ( serviceRequestDTO == null ) {
            return null;
        }

        ServiceDOBuilder serviceDO = ServiceDO.builder();

        serviceDO.name( serviceRequestDTO.getName() );
        serviceDO.unitMeasure( serviceRequestDTO.getUnitMeasure() );
        serviceDO.value( serviceRequestDTO.getValue() );

        return serviceDO.build();
    }

    @Override
    public ServiceDO toModel(ServiceResponseDTO serviceResponseDTO) {
        if ( serviceResponseDTO == null ) {
            return null;
        }

        ServiceDOBuilder serviceDO = ServiceDO.builder();

        serviceDO.id( serviceResponseDTO.getId() );
        serviceDO.name( serviceResponseDTO.getName() );
        serviceDO.situation( serviceResponseDTO.getSituation() );
        serviceDO.unitMeasure( serviceResponseDTO.getUnitMeasure() );
        serviceDO.value( serviceResponseDTO.getValue() );

        return serviceDO.build();
    }

    @Override
    public ServiceResponseDTO toResponseDTO(ServiceDO serviceDO) {
        if ( serviceDO == null ) {
            return null;
        }

        ServiceResponseDTOBuilder serviceResponseDTO = ServiceResponseDTO.builder();

        serviceResponseDTO.id( serviceDO.getId() );
        serviceResponseDTO.name( serviceDO.getName() );
        serviceResponseDTO.situation( serviceDO.getSituation() );
        serviceResponseDTO.unitMeasure( serviceDO.getUnitMeasure() );
        serviceResponseDTO.value( serviceDO.getValue() );

        return serviceResponseDTO.build();
    }
}
