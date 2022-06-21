package br.com.wnfasolutions.veterinarian.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestUpdateDTO;
import br.com.wnfasolutions.veterinarian.dto.request.ItemServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.repository.filter.AppointmentFilter;

public interface AppointmentService {

	/**
	 * Método responsável por criar uma nova consulta veterinária
	 * @param appointmentRequestDTO dados necessários para a consulta
	 * @return uma consulta criada
	 * @throws Exception 
	 */
	AppointmentResponseDTO createNewAppointment(AppointmentRequestDTO appointmentRequestDTO) throws Exception;

	/**
	 * Listar todas as consultas
	 * @param appointmentFilter
	 * @param pageable
	 * @return
	 */
	Page<AppointmentResponseDTO> findAll(AppointmentFilter appointmentFilter, Pageable pageable);

	/**
	 * Finalizar uma consulta atentida através do identificador
	 * @param id - Identificador da consulta
	 * @throws Exception 
	 */
	void completeAppointment(Long id) throws Exception;

	/**
	 * Cancelar uma consulta através do identificador
	 * @param id - Identificador da consulta
	 * @throws Exception 
	 */
	void cancelAppointment(Long id) throws Exception;

	/**
	 * Busca consulta através do identificador
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	AppointmentResponseDTO findOneById(Long id) throws Exception;

	/**
	 * Atualiza as informações de uma consulta existente
	 * @param id
	 * @param appointmentRequestUpdateDTO
	 * @throws Exception 
	 */
	AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestUpdateDTO appointmentRequestUpdateDTO) throws Exception;

	/**
	 * Incluir um novo item em consulta existente
	 * @param id
	 * @param itemServiceRequestDTO 
	 * @throws Exception 
	 */
	void incluirItemAppointment(Long idAppointment, List<ItemServiceRequestDTO> itemServiceRequestDTO) throws Exception;

	/**
	 * Remover um item em consulta existente
	 * @param idAppointment
	 * @param idItem
	 * @throws Exception 
	 */
	void removerItemAppointment(Long idAppointment, Long idItem) throws Exception;

	
}
