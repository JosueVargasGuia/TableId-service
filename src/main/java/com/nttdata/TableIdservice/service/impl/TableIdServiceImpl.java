package com.nttdata.TableIdservice.service.impl;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.TableIdservice.entity.TableId;
import com.nttdata.TableIdservice.repository.TableIdRepository;
import com.nttdata.TableIdservice.service.TableIdService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TableIdServiceImpl implements TableIdService {
	Logger log = LoggerFactory.getLogger(TableIdService.class);
	@Autowired
	TableIdRepository tableIdRepository;

	@Override
	public Flux<TableId> findAll() {
		// TODO Auto-generated method stub
		return tableIdRepository.findAll();
	}

	@Override
	public Mono<TableId> findById(String nameTable) {
		// TODO Auto-generated method stub
		return tableIdRepository.findById(nameTable);
	}

	@Override
	public Mono<TableId> save(TableId tableId) {
		// TODO Auto-generated method stub
		return tableIdRepository.insert(tableId);
	}

	@Override
	public Mono<TableId> update(TableId tableId) {
		// TODO Auto-generated method stub
		return tableIdRepository.save(tableId);
	}

	@Override
	public Mono<Void> delete(String name) {
		// TODO Auto-generated method stub
		return tableIdRepository.deleteById(name);
	}

	@Override
	public Mono<Long> generateKey(String nameTable) {
	 return tableIdRepository.findById(nameTable)
				.defaultIfEmpty(new TableId(nameTable, Long.valueOf(-1)))
				.flatMap(tableId->{
					if (tableId.getSecuencia()>=1) {
						log.info("tableId:"+tableId.toString());
						tableId.setSecuencia(tableId.getSecuencia()+1);			
						return this.update(tableId).flatMap(_obj -> {
							log.info("update[generateKey]:" + _obj.toString());
							return  Mono.just(_obj.getSecuencia());
						});						
					} else {
						tableId = new TableId(nameTable, Long.valueOf(1));			
						return this.save(tableId).flatMap(_obj -> {
							log.info("save[generateKey]:" + _obj.toString());
							return Mono.just(_obj.getSecuencia());
						});						
					}					 
				}); 
		
	}

}
