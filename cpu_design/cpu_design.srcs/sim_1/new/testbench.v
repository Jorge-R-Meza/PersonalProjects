`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:51:09 PM
// Design Name: 
// Module Name: testbench
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
module testbench();
    reg clk;
    
    wire [31:00] pc;
    wire [31:00] dinstOut;
    wire [31:00] instOut;
    wire ewreg;
    wire em2reg;
    wire ewmem;
    wire [3:00] ealuc;
    wire ealuimm;
    wire [4:00] edestReg;
    wire [31:00] eqa;
    wire [31:00] eqb;
    wire [31:00] eimm32;
    wire mwreg;
    wire mm2reg;
    wire mwmem;
    wire [4:0] mdestReg;
    wire [31:0] mr;
    wire [31:0] mqb;
    wire wwreg;
    wire wm2reg;
    wire [4:0] wdestReg;
    wire [31:0] wr;
    wire [31:0] wdo;
    
datapath dataPath(clk,pc, dinstOut, instOut, ewreg, em2reg, ewmem, ealuc, ealuimm, edestReg, eqa,
 eqb, eimm32, mwreg, mm2reg, mwmem, mdestReg, mr, mqb, wwreg, wm2reg, wdestReg, wr, wdo);
                    
 initial 
   begin
       clk <= 0;
   end
   always begin 
       #5
       clk <= ~clk; //every 5 ns clk flips
   end     
endmodule
