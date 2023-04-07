`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/18/2022 05:15:56 PM
// Design Name: 
// Module Name: DM_MWMEM
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
module DM_MWMEM(
    input clk, 
    input wire [31:0] mr,
    input wire [31:0] mqb,
    input wire mwmem,
    
    output reg [31:0] mdo
    
    );
    
reg[31:0] memoryDM [0:63];
initial //sets the initial alues of the first 10 memory slots according to the instruction
begin
    memoryDM[0] = 32'b10100000000000000000000010101010;
    memoryDM[1] = 32'b00010000000000000000000000010001;
    memoryDM[2] = 32'b00100000000000000000000000100010;
    memoryDM[3] = 32'b00110000000000000000000000110011;
    memoryDM[4] = 32'b01000000000000000000000001000100; 
    memoryDM[5] = 32'b01010000000000000000000001010101;
    memoryDM[6] = 32'b01100000000000000000000001100110;
    memoryDM[7] = 32'b01110000000000000000000001110111;
    memoryDM[8] = 32'b10000000000000000000000010001000;
    memoryDM[9] = 32'b10010000000000000000000010011001;
end

always @(*)
begin
    mdo = memoryDM[mr];
end

always @(negedge clk)
begin
    if (mwmem ==1) 
    begin
        memoryDM[mr] = mqb;
    end
end
endmodule
