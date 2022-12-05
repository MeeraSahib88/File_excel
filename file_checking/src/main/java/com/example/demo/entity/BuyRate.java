package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buy_rate")
@Builder
public class BuyRate {

	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String id;

	@Column(name = "time_from")
	private LocalTime fromTime;

	@Column(name = "time_to")
	private LocalTime toTime;

	@Column(name = "power_rate")
	private double powerRate;

	@Column(name = "price_rate")
	private double priceRate;

	@Column(name = "buy_date")
	private LocalDate date;

	@Column(name = "created_date")
	private DateTime createdDate;

	@Column(name = "updated_date")
	private DateTime updatedDate;
}
