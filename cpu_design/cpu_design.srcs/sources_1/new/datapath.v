`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: datapath
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////

//intializes the entire project
module datapath(
    input  clk,
    
    output wire [31:0] pc,
    output wire [31:0] dinstOut,instOut,
    output wire ewreg,
    output wire em2reg,
    output wire ewmem,
    output wire [3:0] ealuc,
    output wire ealuimm,
    output wire [4:0] edestReg,
    output wire [31:0] eqa,
    output wire [31:0] eqb,
    output wire [31:0] eimm32,
    output wire mwreg,
    output wire mm2reg,
    output wire mwmem,
    output wire [4:0] mdestReg,
    output wire [31:0] mr,
    output wire [31:0] mqb,
    output wire wwreg,
    output wire wm2reg,
    output wire [4:0] wdestReg,
    output wire [31:0] wr,
    output wire [31:0] wdo,
    output wire [31:0] wbData,
    output wire [1:0] fwdb, fwda
    );

    wire [31:00] nextPC;
    wire wreg;
    wire m2reg;
    wire wmem;
    wire [3:0] aluc;
    wire aluimm;
    wire regrt;
    wire fwda;
    wire fwdb;
    
    wire [4:0] rs = dinstOut[25:21]; 
    wire [31:0] qa;
    wire [31:0] qb;
    
    
    wire [31:0] imm32;
    wire [4:0] rt = dinstOut[20:16];
    wire [4:0] rd = dinstOut[15:11];
    
    wire [4:0] destReg;
    wire [31:0] b;
    wire [31:0] r;
    
    
    wire [31:0] mdo;
    
   // wire [31:00] instOut;
    
    wire [5:00] op = dinstOut[31:26];
    wire [5:00] func = dinstOut[5:00];
    wire [31:0] Afwd, Bfwd;
   // wire [31:0] wbData;
    
    program_counter programCounter(clk, nextPC, pc);
    
    pc_adder pcAdder(pc, nextPC);
    
    
    
    instruction_memory instructionMemory(pc, instOut);
    
    IFID ifid(clk,instOut, dinstOut);
    
    
    immediate_extender immExtender(dinstOut[15:0], imm32);
    
    register_file registerFile(clk, rs, rt, wdestReg, wbData, wwreg, Afwd, bFwd);
    
    Wb_Mux wb_mux(wr, wdo, wm2reg, wbData);
    
    IDEXE idexe(clk, wreg, m2reg, wmem, aluc, aluimm, destReg, qa, qb, imm32, ewreg,em2reg, ewmem, ealuc, ealuimm, edestReg, eqa, eqb, eimm32);
    
    control_unit controlUnit(op, func,rs, rt, edestReg, mdestReg, mm2reg, mwreg, em2reg, ewreg, wreg, m2reg, wmem,
    aluc, aluim, regrt, fwda, fwdb);
    
    
    regrt_mux regrtMux(rt, rd, regrt, destReg);
    
    ALU alu(eqa, b, ealuc, r);
    
    ALU_Mux alu_mux(eqb, eimm32, ealuimm, b);
    
    EXEMEM exemem(clk, ewreg, em2reg, ewmem, edestReg, r, eqb, mwreg, mm2reg, mwmem, mdestReg, mr, mqb);
    
    DM_MWMEM dm_mwmem(clk, mr, mqb, mwmem, mdo);
    
    MEMWB memwb(clk, mwreg, mm2reg, mdestReg, mr, mdo, wwreg, wm2reg, wdestReg, wr, wdo);
    
    
    FwdA_Mux FwdA(fwda, qa, r, mr, mdo, Afwd);
    FwdB_Mux FwdB(fwdb, qb, r, mr, mdo, Bfwd);
    
   

endmodule
