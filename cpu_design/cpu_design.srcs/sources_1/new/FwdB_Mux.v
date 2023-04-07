`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 12/09/2022 03:40:28 PM
// Design Name: 
// Module Name: FwdB_Mux
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


module FwdB_Mux(
    input wire [1:0] fwdb,
    input wire [31:0] qb,
    input wire [31:0] r,
    input wire [31:0] mr,
    input wire [31:0] mdo,
    
    output reg [31:0]Bfwd 
    );
    
    always @ (*)
    begin
        case(fwdb)
        // add cases 00, 01, 10, 11
        2'b00:
            Bfwd  = qb;
        2'b01:
            Bfwd  = r;
        2'b10:
            Bfwd  = mr;
        2'b11: 
            Bfwd  = mdo;
        endcase
    end


endmodule
