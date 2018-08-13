package com.dellemc.devops.meta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dellemc.devops.meta.MetaData;
import com.dellemc.devops.meta.MetadataRepository;
import com.dellemc.devops.meta.metadataNotFoundException;

@RestController
public class MetaDataController {

	@Autowired
	MetadataRepository metadataRepository;

	@GetMapping("/")
	public String hello() {
		return "metadata app is up and running..!!!";
	}

	@GetMapping("/metadata/all")
	public List<MetaData> retrieveAllMetaData() {
		return metadataRepository.findAll();
	}

	@GetMapping("/metadata/{id}")
	public MetaData retrieveMetaDataByKey(@PathVariable String id) {
		Optional<MetaData> metaData = metadataRepository.findById(id);
		if (!metaData.isPresent())
			throw new metadataNotFoundException("id-" + id);
		return metaData.get();
	}

	@DeleteMapping("/metadata/{id}")
	public void deleteMetaData(@PathVariable String id) {
		metadataRepository.deleteById(id);
	}

	@PostMapping("/metadata")
	public MetaData createMetaData(@RequestBody MetaData metadata) {
		return metadataRepository.save(metadata);
	}

	@PutMapping("/metaData/{id}")
	public ResponseEntity<MetaData> updateMetaData(@RequestBody MetaData metaData, @PathVariable String id) {
		Optional<MetaData> metadataOptional = metadataRepository.findById(id);

		if (!metadataOptional.isPresent())
			return ResponseEntity.notFound().build();
		metaData.setKey(id);
		metadataRepository.save(metaData);
		return new ResponseEntity<MetaData>(metaData, HttpStatus.OK);
	}
}
