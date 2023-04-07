`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/18/2022 05:15:56 PM
// Design Name: 
// Module Name: MEMWB
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
module MEMWB(
    input clk,
    input wire mwreg,
    input wire mm2reg,
    input wire [4:0] mdestReg,
    input wire [31:0] mr,
    input wire [31:0] mdo,
    
    output reg wwreg,
    output reg wm2reg,
    output reg [4:0] wdestReg,
    output reg [31:0] wr,
    output reg [31:0] wdo
    );
    
always @(posedge clk)
begin
    wwreg = mwreg;
    wm2reg = mm2reg;
    wdestReg = mdestReg;
    wr = mr;
    wdo = mdo;
end
endmodule
