package com.example.exemplesspringbatch.processor;

import com.example.exemplesspringbatch.model.Person;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public @Nullable Person process(Person item) throws Exception {
        return new Person(item.firstName().toUpperCase(), item.lastName().toUpperCase());
    }
}
