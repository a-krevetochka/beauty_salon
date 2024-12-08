package com.mgtu.beauty.service;

import com.mgtu.beauty.controller.RecordController.response.GetRecordResponse;
import com.mgtu.beauty.entity.Record;
import com.mgtu.beauty.controller.RecordController.request.CreateRecordRequest;
import com.mgtu.beauty.entity.TimeSlot;
import com.mgtu.beauty.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public List<TimeSlot> getTimeSlots(LocalDate date, Integer masterId) {
        return recordRepository.findFreeTimeSlots(date, masterId);
    }

    public void createRecord(CreateRecordRequest dto) {
        recordRepository.save(Record.builder()
                .serviceId(dto.getServiceId())
                .timeSlotId(dto.getTimeSlotId())
                .masterId(dto.getMasterId())
                .date(dto.getDate())
                .userId(dto.getUserId())
                .build());
    }

    public List<GetRecordResponse> getUserRecords(String phoneFromToken) {
        return recordRepository.findByUserPhone(phoneFromToken);
    }

    public List<GetRecordResponse> getActiveRecords() {
        return recordRepository.findActive();
    }

    public void deleteRecord(Integer recordId) {
        recordRepository.delete(recordId);
    }
}
