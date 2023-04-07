`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/18/2022 05:15:56 PM
// Design Name: 
// Module Name: ALU_Mux
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
module ALU_Mux(
    input wire [31:0] eqb,
    input wire [31:0] eimm32,
    input wire ealuimm,
    
    output reg [31:0] b

    );
always @(*)
begin
    if (ealuimm == 0) //sets the value of b according to the value of ealuimm
    begin
        b = eqb;
    end
    else if (ealuimm == 1)
    begin
        b = eimm32;
    end
end

endmodule
