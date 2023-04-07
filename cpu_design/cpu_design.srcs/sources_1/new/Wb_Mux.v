`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 12/05/2022 06:29:48 PM
// Design Name: 
// Module Name: Wb_Mux
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

module Wb_Mux(
    input wire [31:0] wr,
    input wire [31:0] wdo,
    input wire wm2reg,
    
    output reg [31:0] wbData
    );
    
always @(*)
begin
    if (wm2reg == 0) //sets wbData to wr if wm2reg = 0
    begin
        wbData = wr;
    end
    else if (wm2reg == 1)
    begin
        wbData = wdo;
    end
end
endmodule
