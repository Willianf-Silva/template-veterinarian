package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO.ClientResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;
import br.com.wnfasolutions.veterinarian.entity.ClientDO.ClientDOBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:47:01-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDO toModel(ClientRequestDTO clientRequestDTO) {
        if ( clientRequestDTO == null ) {
            return null;
        }

        ClientDOBuilder clientDO = ClientDO.builder();

        clientDO.apelido( clientRequestDTO.getApelido() );
        clientDO.birthDate( clientRequestDTO.getBirthDate() );
        clientDO.cpf( clientRequestDTO.getCpf() );
        clientDO.email( clientRequestDTO.getEmail() );
        clientDO.firstName( clientRequestDTO.getFirstName() );
        clientDO.lastName( clientRequestDTO.getLastName() );

        return clientDO.build();
    }

    @Override
    public ClientResponseDTO toResponseDTO(ClientDO clientDO) {
        if ( clientDO == null ) {
            return null;
        }

        ClientResponseDTOBuilder clientResponseDTO = ClientResponseDTO.builder();

        clientResponseDTO.apelido( clientDO.getApelido() );
        clientResponseDTO.birthDate( clientDO.getBirthDate() );
        clientResponseDTO.cpf( clientDO.getCpf() );
        clientResponseDTO.email( clientDO.getEmail() );
        clientResponseDTO.firstName( clientDO.getFirstName() );
        clientResponseDTO.id( clientDO.getId() );
        clientResponseDTO.lastName( clientDO.getLastName() );
        clientResponseDTO.situation( clientDO.getSituation() );

        return clientResponseDTO.build();
    }
}
