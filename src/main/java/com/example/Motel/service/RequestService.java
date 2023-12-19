package com.example.Motel.service;

import com.example.Motel.dto.RequestDto;
import com.example.Motel.model.Guest;
import com.example.Motel.model.Request;
import com.example.Motel.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public Request create(Guest guest, RequestDto requestDto) {
        Request request = new Request();
        request.setDate(requestDto.getDate());
        request.setRequestType(requestDto.getRequestType());
        request.setGuest(guest);
        requestRepository.save(request);
        return request;
    }

    public boolean delete(Long request_id) {
        boolean findRequest = this.existRequest(request_id);

        if (findRequest) {
            requestRepository.deleteById(request_id);
            return true;
        }
        return false;
    }

    public void update(Long request_id, RequestDto updateRequest) {
        boolean findRequest = this.existRequest(request_id);

        if (findRequest) {

        }
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public boolean existRequest(Long request_id) {
        return requestRepository.existsById(request_id);
    }
}
