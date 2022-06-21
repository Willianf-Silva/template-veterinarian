package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.response.ItemServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ItemServiceResponseDTO.ItemServiceResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO.ServiceResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:33:47-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class ItemServiceMapperImpl implements ItemServiceMapper {

    @Override
    public ItemServiceResponseDTO toResponseDTO(ItemServiceDO itemServiceDO) {
        if ( itemServiceDO == null ) {
            return null;
        }

        ItemServiceResponseDTOBuilder itemServiceResponseDTO = ItemServiceResponseDTO.builder();

        itemServiceResponseDTO.id( itemServiceDO.getId() );
        itemServiceResponseDTO.quantity( itemServiceDO.getQuantity() );
        itemServiceResponseDTO.service( serviceDOToServiceResponseDTO( itemServiceDO.getService() ) );
        itemServiceResponseDTO.sum( itemServiceDO.getSum() );

        return itemServiceResponseDTO.build();
    }

    protected ServiceResponseDTO serviceDOToServiceResponseDTO(ServiceDO serviceDO) {
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
