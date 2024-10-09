import java.util.Scanner;


public class CPU {
   /*
   8-bit data bus
   8-bit registers
   4-bit address bus -> 16 bytes RAM
   4 instructions: LDA, ADD, OUT, HLT
   2 control flags: output and halt
   */


    private int[] RAM;  // addressable memory
    private int MAR;
    private int MDR;
    private int CIR;
    private int PC;
    private int ACC;
    private int OUT;


    // control flags
    private boolean output;
    private boolean halt;


    public CPU(int[] program) {
        RAM = new int[16];
        // copy program into RAM
        int i = 0;
        while (i<program.length && i<RAM.length) {
            RAM[i] = program[i];
            i++;
        }
        // init registers & flags
        MAR = 0;
        MDR = 0;
        CIR = 0;
        PC = 0;
        ACC = 0;
        OUT = 0;
        output = false;
        halt = false;
    }


    // display RAM and registers
    public void showCurrentState() {
        System.out.println(" PC:" + PC);
        System.out.println("MAR:" + MAR);
        System.out.println("MDR:" + MDR);
        System.out.println("CIR:" + CIR);
        System.out.println("ACC:" + ACC);
        System.out.println("OUT:" + OUT);
        // show RAM
        System.out.print("RAM:");
        for (int i=0; i<RAM.length; i++) {
            System.out.print(RAM[i] + " ");
        }
        System.out.println("\n------------------");
    }


    public void run() {
        while (!halt) {
            // fetch-execute cycle
            fetch();
            //decode();
            //execute();
            showCurrentState();
            Scanner pause = new Scanner(System.in);
            pause.nextLine();
        }
    }


    public void fetch() {
        // copy PC to MAR
        MAR = PC;
        // fetch from MAR address to MDR
        MDR = RAM[MAR];
        // copy MDR to CIR
        CIR = MDR;
        // increment PC for the step
        PC++;
        if (PC >= RAM.length) {  // wrap at the top of mem
            PC = 0;
        }
    }


    public void decode() {
        // split instruction into op-code and operand
        int opCode = CIR & 0b11110000; // top 4 bits
        int operand = CIR & 0b00001111; // bottom 4 bits
    }










}
