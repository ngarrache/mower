package com.mowitnow.mowerautomaton;

import static com.mowitnow.mowerautomaton.model.Instruction.*;
import static com.mowitnow.mowerautomaton.model.Orientation.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mowitnow.mowerautomaton.ProgramGenerator;
import com.mowitnow.mowerautomaton.exception.InvalidFileFormatException;
import com.mowitnow.mowerautomaton.model.Instruction;
import com.mowitnow.mowerautomaton.model.Orientation;
import com.mowitnow.mowerautomaton.model.Position;
import com.mowitnow.mowerautomaton.model.Program;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class ProgramGeneratorTest {
	private ProgramGenerator programGenerator;
	private Program program;

	@Before
	public void setUp() throws Exception {
		programGenerator = new ProgramGenerator();
		program = new Program();
	}

	@Test
	public void getOrientation_InputN_ReturnNorth()
			throws InvalidFileFormatException {
		Orientation result = programGenerator.getOrientation("N");

		assertThat(result).isEqualTo(NORTH);
	}

	@Test
	public void getOrientation_InputE_ReturnEast()
			throws InvalidFileFormatException {
		Orientation result = programGenerator.getOrientation("E");

		assertThat(result).isEqualTo(EAST);
	}

	@Test
	public void getOrientation_InputS_ReturnSouth()
			throws InvalidFileFormatException {
		Orientation result = programGenerator.getOrientation("S");

		assertThat(result).isEqualTo(SOUTH);
	}

	@Test
	public void getOrientation_InputW_ReturnWest()
			throws InvalidFileFormatException {
		Orientation result = programGenerator.getOrientation("W");

		assertThat(result).isEqualTo(WEST);
	}

	@Test(expected = InvalidFileFormatException.class)
	public void getOrientation_InputX_ThrowError()
			throws InvalidFileFormatException {
		programGenerator.getOrientation("X");
	}

	@Test
	public void getInstruction_InputD_ReturnRight()
			throws InvalidFileFormatException {
		Instruction result = programGenerator.getInstruction('D');

		assertThat(result).isEqualTo(RIGHT);
	}

	@Test
	public void getInstruction_InputG_ReturnLeft()
			throws InvalidFileFormatException {
		Instruction result = programGenerator.getInstruction('G');

		assertThat(result).isEqualTo(LEFT);
	}

	@Test
	public void getInstruction_InputA_ReturnForward()
			throws InvalidFileFormatException {
		Instruction result = programGenerator.getInstruction('A');

		assertThat(result).isEqualTo(FORWARD);
	}

	@Test(expected = InvalidFileFormatException.class)
	public void getInstruction_InputX_ThrowError()
			throws InvalidFileFormatException {
		programGenerator.getInstruction('X');
	}

	@Test
	public void setMaxXAndMaxY_WellFormatedString_SetXY()
			throws InvalidFileFormatException {
		programGenerator.setMaxXAndMaxY(program, "5 5");

		assertThat(program.getMaxX()).isEqualTo(5);
		assertThat(program.getMaxY()).isEqualTo(5);
	}

	@Test(expected = InvalidFileFormatException.class)
	public void setMaxXAndMaxY_NullString_ThrowException()
			throws InvalidFileFormatException {
		programGenerator.setMaxXAndMaxY(program, null);
	}

	@Test(expected = InvalidFileFormatException.class)
	public void setMaxXAndMaxY_BadString_ThrowException()
			throws InvalidFileFormatException {
		programGenerator.setMaxXAndMaxY(program, "55");
	}

	@Test(expected = InvalidFileFormatException.class)
	public void setMaxXAndMaxY_NotNumberString_ThrowException()
			throws InvalidFileFormatException {
		programGenerator.setMaxXAndMaxY(program, "XXX");
	}

	@Test
	public void addCommand_InputOk_AddCommandToProgram()
			throws InvalidFileFormatException {
		programGenerator.addCommand(program, "1 2 N", "GAGAGAGAA");

		Position po1 = new Position(1, 2, NORTH);
		assertThat(program.getPositions()).containsOnly(po1);
		assertThat(program.getInstructionsByMower(po1)).containsExactly(LEFT,
				FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
	}

	@Test
	public void generate_Ok_ReturnProgram() throws IOException,
			InvalidFileFormatException {
		BufferedReader readerStub = mock(BufferedReader.class);
		when(readerStub.readLine()).thenReturn("5 5").thenReturn("1 2 N")
				.thenReturn("GAGAGAGAA").thenReturn("3 3 E")
				.thenReturn("AADAADADDA").thenReturn(null);
		programGenerator.setReaderForTesting(readerStub);

		Program program = programGenerator.generate();

		assertThat(program.getMaxX()).isEqualTo(5);
		assertThat(program.getMaxY()).isEqualTo(5);
		Position po1 = new Position(1, 2, NORTH);
		Position po2 = new Position(3, 3, EAST);
		assertThat(program.getPositions()).containsOnly(po1, po2);
		// check for GAGAGAGAA
		assertThat(program.getInstructionsByMower(po1)).containsExactly(LEFT,
				FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD);
		// check for AADAADADDA
		assertThat(program.getInstructionsByMower(po2)).containsExactly(
				FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD,
				RIGHT, RIGHT, FORWARD);
	}
}
