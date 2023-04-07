`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: immediate_extender
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

module immediate_extender(
    input wire [15:0] imm,
    
    output reg[31:0] imm32
    );
 always @(*)
 begin
    imm32 = {{16{imm[15]}},imm}; //extends imm by 16 bits
 end 
endmodule
