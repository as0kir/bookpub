package org.test.bookpub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.test.bookpub.repository.PublisherRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookPubApplication.class, TestMockBeansConfig.class})
public class PublisherRepositoryTests {
    @Autowired
    private PublisherRepository repository;

    @Before
    public void setupPublisherRepositoryMock(){
        Mockito.when(repository.count()).thenReturn(1L);
    }

    @Test
    public void publicherExists(){
        assertEquals(1, repository.count());
    }

    @After
    public void resetPublicherRepositoryMock(){
        Mockito.reset(repository);
    }
}
