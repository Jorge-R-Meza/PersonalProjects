`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: instruction_memory
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

module instruction_memory(
    input wire [31:0] pc,
    
    output reg[31:0] instOut
    );
    reg[31:0] memory [0:63]; //makes the memory array
    
initial begin    
// change to new instructions
memory[25] ={6'b100011,5'b00001,5'b00010,5'b00000,5'b00000,6'b000000}; // first lw instruction
memory[26] ={6'b100011,5'b00001,5'b00011,5'b00000,5'b00000,6'b000100}; // second lw instruction
memory[27] ={6'b100011,5'b00001,5'b00100,5'b00000,5'b00000,6'b001000}; // third lw instruction
memory[28] ={6'b100011,5'b00001,5'b00101,5'b00000,5'b00000,6'b001100}; // fourth lw instruction
memory[29] ={6'b000000,5'b00010,5'b01010,5'b00110,5'b00000,6'b100000}; // add instruction
end
always @(*)
begin
    instOut = memory[pc[7:2]]; 
end


endmodule
