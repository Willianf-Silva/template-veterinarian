package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.ItemServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO.RoleRequestDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO.AppointmentResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO.ClientResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.ItemServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ItemServiceResponseDTO.ItemServiceResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO.ServiceResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO.VeterinarianResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;
import br.com.wnfasolutions.veterinarian.entity.AppointmentDO.AppointmentDOBuilder;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;
import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO;
import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO.ItemServiceDOBuilder;
import br.com.wnfasolutions.veterinarian.entity.RoleDO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:59:31-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDO toModel(AppointmentRequestDTO appointmentRequestDTO) {
        if ( appointmentRequestDTO == null ) {
            return null;
        }

        AppointmentDOBuilder appointmentDO = AppointmentDO.builder();

        appointmentDO.date( appointmentRequestDTO.getDate() );
        appointmentDO.itemService( itemServiceRequestDTOListToItemServiceDOList( appointmentRequestDTO.getItemService() ) );

        return appointmentDO.build();
    }

    @Override
    public AppointmentResponseDTO toResponseDTO(AppointmentDO appointmentDO) {
        if ( appointmentDO == null ) {
            return null;
        }

        AppointmentResponseDTOBuilder appointmentResponseDTO = AppointmentResponseDTO.builder();

        appointmentResponseDTO.client( clientDOToClientResponseDTO( appointmentDO.getClient() ) );
        appointmentResponseDTO.date( appointmentDO.getDate() );
        appointmentResponseDTO.id( appointmentDO.getId() );
        appointmentResponseDTO.itemService( itemServiceDOListToItemServiceResponseDTOList( appointmentDO.getItemService() ) );
        appointmentResponseDTO.status( appointmentDO.getStatus() );
        appointmentResponseDTO.total( appointmentDO.getTotal() );
        appointmentResponseDTO.veterinarian( veterinarianDOToVeterinarianResponseDTO( appointmentDO.getVeterinarian() ) );

        return appointmentResponseDTO.build();
    }

    protected ItemServiceDO itemServiceRequestDTOToItemServiceDO(ItemServiceRequestDTO itemServiceRequestDTO) {
        if ( itemServiceRequestDTO == null ) {
            return null;
        }

        ItemServiceDOBuilder itemServiceDO = ItemServiceDO.builder();

        itemServiceDO.quantity( itemServiceRequestDTO.getQuantity() );

        return itemServiceDO.build();
    }

    protected List<ItemServiceDO> itemServiceRequestDTOListToItemServiceDOList(List<ItemServiceRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemServiceDO> list1 = new ArrayList<ItemServiceDO>( list.size() );
        for ( ItemServiceRequestDTO itemServiceRequestDTO : list ) {
            list1.add( itemServiceRequestDTOToItemServiceDO( itemServiceRequestDTO ) );
        }

        return list1;
    }

    protected ClientResponseDTO clientDOToClientResponseDTO(ClientDO clientDO) {
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

    protected ItemServiceResponseDTO itemServiceDOToItemServiceResponseDTO(ItemServiceDO itemServiceDO) {
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

    protected List<ItemServiceResponseDTO> itemServiceDOListToItemServiceResponseDTOList(List<ItemServiceDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemServiceResponseDTO> list1 = new ArrayList<ItemServiceResponseDTO>( list.size() );
        for ( ItemServiceDO itemServiceDO : list ) {
            list1.add( itemServiceDOToItemServiceResponseDTO( itemServiceDO ) );
        }

        return list1;
    }

    protected RoleRequestDTO roleDOToRoleRequestDTO(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleRequestDTOBuilder roleRequestDTO = RoleRequestDTO.builder();

        roleRequestDTO.roleName( roleDO.getRoleName() );

        return roleRequestDTO.build();
    }

    protected Set<RoleRequestDTO> roleDOSetToRoleRequestDTOSet(Set<RoleDO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleRequestDTO> set1 = new HashSet<RoleRequestDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDO roleDO : set ) {
            set1.add( roleDOToRoleRequestDTO( roleDO ) );
        }

        return set1;
    }

    protected VeterinarianResponseDTO veterinarianDOToVeterinarianResponseDTO(VeterinarianDO veterinarianDO) {
        if ( veterinarianDO == null ) {
            return null;
        }

        VeterinarianResponseDTOBuilder veterinarianResponseDTO = VeterinarianResponseDTO.builder();

        veterinarianResponseDTO.birthDate( veterinarianDO.getBirthDate() );
        veterinarianResponseDTO.cpf( veterinarianDO.getCpf() );
        veterinarianResponseDTO.crmv( veterinarianDO.getCrmv() );
        veterinarianResponseDTO.email( veterinarianDO.getEmail() );
        veterinarianResponseDTO.firstName( veterinarianDO.getFirstName() );
        veterinarianResponseDTO.id( veterinarianDO.getId() );
        veterinarianResponseDTO.lastName( veterinarianDO.getLastName() );
        veterinarianResponseDTO.roles( roleDOSetToRoleRequestDTOSet( veterinarianDO.getRoles() ) );
        veterinarianResponseDTO.situation( veterinarianDO.getSituation() );
        veterinarianResponseDTO.username( veterinarianDO.getUsername() );

        return veterinarianResponseDTO.build();
    }
}
