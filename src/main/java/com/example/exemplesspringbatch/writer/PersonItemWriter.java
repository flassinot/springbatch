package com.example.exemplesspringbatch.writer;

import com.example.exemplesspringbatch.model.Person;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class PersonItemWriter implements ItemWriter<Person> {
    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        chunk.forEach(p -> System.out.println("Person processed : " + p.firstName() + " " + p.lastName()));
    }
}
