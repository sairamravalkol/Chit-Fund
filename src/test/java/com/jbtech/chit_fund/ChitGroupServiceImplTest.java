package com.jbtech.chit_fund;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.repository.ChitGroupRepository;
import com.jbtech.chit_fund.service.ChitGroupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChitGroupServiceImplTest {

    @Mock
    private ChitGroupRepository chitGroupRepository;

    @InjectMocks
    private ChitGroupServiceImpl chitGroupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChitGroup() {
        ChitGroup chitGroup = new ChitGroup();
        chitGroup.setName("Test Group");

        when(chitGroupRepository.save(chitGroup)).thenReturn(chitGroup);

        ChitGroup createdChitGroup = chitGroupService.createChitGroup(chitGroup);

        assertNotNull(createdChitGroup);
        assertEquals("Test Group", createdChitGroup.getName());
        verify(chitGroupRepository, times(1)).save(chitGroup);
    }

    @Test
    void testGetChitGroupById_Found() {
        ChitGroup chitGroup = new ChitGroup();
        chitGroup.setId(1L);

        when(chitGroupRepository.findById(1L)).thenReturn(Optional.of(chitGroup));

        Optional<ChitGroup> foundChitGroup = chitGroupService.getChitGroupById(1L);

        assertTrue(foundChitGroup.isPresent());
        assertEquals(1L, foundChitGroup.get().getId());
        verify(chitGroupRepository, times(1)).findById(1L);
    }

    @Test
    void testGetChitGroupById_NotFound() {
        when(chitGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chitGroupService.getChitGroupById(1L));
        verify(chitGroupRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllChitGroups() {
        ChitGroup chitGroup1 = new ChitGroup();
        ChitGroup chitGroup2 = new ChitGroup();

        when(chitGroupRepository.findAll()).thenReturn(Arrays.asList(chitGroup1, chitGroup2));

        var chitGroups = chitGroupService.getAllChitGroups();

        assertEquals(2, chitGroups.size());
        verify(chitGroupRepository, times(1)).findAll();
    }

    @Test
    void testUpdateChitGroup_Found() {
        ChitGroup existingChitGroup = new ChitGroup();
        existingChitGroup.setId(1L);
        existingChitGroup.setName("Old Name");

        ChitGroup updatedChitGroup = new ChitGroup();
        updatedChitGroup.setName("New Name");

        when(chitGroupRepository.findById(1L)).thenReturn(Optional.of(existingChitGroup));
        when(chitGroupRepository.save(existingChitGroup)).thenReturn(existingChitGroup);

        ChitGroup result = chitGroupService.updateChitGroup(1L, updatedChitGroup);

        assertEquals("New Name", result.getName());
        verify(chitGroupRepository, times(1)).findById(1L);
        verify(chitGroupRepository, times(1)).save(existingChitGroup);
    }

    @Test
    void testUpdateChitGroup_NotFound() {
        ChitGroup updatedChitGroup = new ChitGroup();

        when(chitGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chitGroupService.updateChitGroup(1L, updatedChitGroup));
        verify(chitGroupRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteChitGroup_Found() {
        when(chitGroupRepository.existsById(1L)).thenReturn(true);

        chitGroupService.deleteChitGroup(1L);

        verify(chitGroupRepository, times(1)).existsById(1L);
        verify(chitGroupRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteChitGroup_NotFound() {
        when(chitGroupRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> chitGroupService.deleteChitGroup(1L));
        verify(chitGroupRepository, times(1)).existsById(1L);
    }
}