package com.poc.performance.application;

import com.poc.performance.domain.Countries;

@FunctionalInterface
public interface CountryClient {
    Countries countries();
}
