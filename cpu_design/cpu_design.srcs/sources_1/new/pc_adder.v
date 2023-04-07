`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: pc_adder
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

module pc_adder(
    input wire [31:0] pc,
   
    output reg[31:0] nextPC

    );
always @(*)
    begin 
    nextPC <= pc + 32'd4; //adds 4 to pc to create nextPC
    end
endmodule
