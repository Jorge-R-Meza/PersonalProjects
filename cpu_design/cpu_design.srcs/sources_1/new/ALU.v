`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/18/2022 05:15:56 PM
// Design Name: 
// Module Name: ALU
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
module ALU(
    input wire [31:0] eqa,
    input wire [31:0] b,
    input wire [3:0] ealuc,
    
    output reg [31:0] r
    );
always @(*)
begin
    // ADD
    if (ealuc == 4'b0010)  //if statemtn that choses operation depending on ealuc value
                           //only one needed for lab 4
    begin
        r = eqa + b;
    end
    // SUB
    if (ealuc == 4'b0110)
    begin
        r <= eqa -b;
    end
    // SOR
    if (ealuc == 4'b0001)
    begin
        r <= eqa | b;
    end
    // XOR
    if (ealuc == 4'b1000)
    begin
        r <= eqa ^ b;
    end
    // AND 
    if (ealuc == 4'b10)
    begin
        r <= eqa & b;
    end
end
endmodule
