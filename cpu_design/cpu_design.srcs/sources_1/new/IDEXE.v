`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: IDEXE
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

module IDEXE(
    input clk,
    input wire wreg,
    input wire m2reg,
    input wire wmem,
    input wire [3:0] aluc,
    input wire aluimm,
    input wire [4:0] destReg,
    input wire [31:0] qa,
    input wire [31:0] qb,
    input wire [31:0] imm32,
    
    output reg ewreg,
    output reg em2reg,
    output reg ewmem,
    output reg [3:0] ealuc,
    output reg ealuimm, 
    output reg [4:0] edestReg,
    output reg [31:0] eqa,
    output reg [31:0] eqb,
    output reg [31:0] eimm32
    );
always @(posedge clk)
begin //sets the values as given in the video
    ewreg = wreg;
    em2reg = m2reg;
    ewmem = wmem;
    ealuc = aluc;
    ealuimm = aluimm;
    edestReg = destReg;
    eqa = qa;
    eqb = qb;
    eimm32 = imm32;
    
end
endmodule
