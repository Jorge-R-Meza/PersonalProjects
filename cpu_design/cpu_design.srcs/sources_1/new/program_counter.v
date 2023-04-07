`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:25:16 PM
// Design Name: 
// Module Name: program_counter
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

module program_counter(
    input wire clk,
    input wire [31:0] nextPC,
    
    output reg[31:0] pc
    );
    initial 
    pc <= 32'd100; //7'b1100100;
    
    always @(posedge clk)
    begin
        pc <= nextPC; //sets pc to nextPC
    end
endmodule
