`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: regrt_mux
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
module regrt_mux(
    input wire [4:0] rt,
    input wire [4:0] rd,
    input wire regrt,
    
    output reg[4:0] destReg
    );
always @(*)
begin
    if (regrt == 0) //sets the value of destReg according to the value of regrt
    begin
        destReg = rd;
    end
    else if (regrt == 1)
    begin
        destReg = rt;
    end   
end
endmodule
