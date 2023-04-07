`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 12/09/2022 03:40:28 PM
// Design Name: 
// Module Name: FwdA_Mux
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


module FwdA_Mux(
    input wire [1:0] fwda,
    input wire [31:0] qa,
    input wire [31:0] r,
    input wire [31:0] mr,
    input wire [31:0] mdo,
    
    output reg [31:0]Afwd
    );
    always @ (*)
    begin
        case(fwda)
        // add cases 00, 01, 10, 11
        2'b00:
            Afwd = qa;
        2'b01:
            Afwd = r;
        2'b10:
            Afwd = mr;
        2'b11: 
            Afwd = mdo;
        endcase
    end
    

endmodule
