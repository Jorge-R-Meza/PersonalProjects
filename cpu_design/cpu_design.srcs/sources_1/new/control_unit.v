`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: control_unit
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

module control_unit(
    input wire [5:0] op, // bits [31:26] of dinstOut 
    input wire [5:0] func, // bits [5:0] of dinstOut
    input wire [4:0] rs,
    input wire [4:0] rt,
    input wire [4:0] edestReg,
    input wire [4:0] mdestReg,
    input wire mm2reg,
    input wire mwreg,
    input wire em2reg,
    input wire ewreg,
    output reg wreg,
    output reg m2reg,
    output reg wmem,
    output reg [3:0] aluc,
    output reg aluimm,
    output reg regrt,
    output reg fwda,
    output reg fwdb
    );
    
always @(*)
begin
    case (op)
        6'b000000:
        begin 
            wreg <= 1;
            m2reg <= 0;
            wmem <= 0;
            aluimm <= 0;
            regrt <= 0;
            case (func)// r-types 
                6'b100000: // ADD 
                begin
                    wreg <= 1'b1;
                    m2reg <= 1'b0; 
                    wmem  <= 1'b0;
                    aluc <= 4'b0010;
                    aluimm <= 1'b0;
                    regrt <= 1'b0;   
                end   
                6'b100101: // OR
                begin
                    wreg <= 1'b1;
                    m2reg <= 1'b0; 
                    wmem  <= 1'b0;
                    aluc <= 4'b0001;
                    aluimm <= 1'b0;
                    regrt <= 1'b0;   
                end
                6'b100111: // XOR
                begin
                    wreg <= 1'b1;
                    m2reg <= 1'b0; 
                    wmem  <= 1'b0;
                    aluc <= 4'b1100;
                    aluimm <= 1'b0;
                    regrt <= 1'b0;   
                end
                6'b100100: // AND
                begin
                    wreg <= 1'b1;
                    m2reg <= 1'b0; 
                    wmem  <= 1'b0;
                    aluc <= 4'b0000;
                    aluimm <= 1'b0;
                    regrt <= 1'b0;   
                end
                6'b100010: // SUB
                begin
                    wreg <= 1'b1;
                    m2reg <= 1'b0; 
                    wmem  <= 1'b0;
                    aluc <= 4'b0110;
                    aluimm <= 1'b0;
                    regrt <= 1'b0;   
                end
            endcase
        end
        6'b100011:          //LW
        begin
            wreg <= 1'b1;
            m2reg <= 1'b1;
            wmem <= 1'b0;
            aluc <= 4'b0010;
            aluimm <= 1'b1;
            regrt <= 1'b1;                 
        end
    endcase
        fwda = 2'b00;
    fwdb = 2'b00;
    
    if(edestReg == rs) begin fwda = 2'b01; end
    if (edestReg == rt) begin fwdb = 2'b01; end
    
    if(mdestReg == rs)begin
        fwda = mm2reg ? 2'b11 : 2'b10; 
        end
    if(mdestReg == rt) begin
        fwdb = mm2reg ? 2'b10 : 2'b11; 
        end
    
    
end

    /*
    if (rt == edestReg) 
    begin
        fwdb <= 2'b01;
    end
    else if (rt == mdestReg)
    begin
        fwdb <= 2'b11;
    end
    else 
    begin
        fwdb <= 2'b00;
    end
    
    if (rs == edestReg)
    begin
        fwda <= 2'b01; 
    end
    else if (rs == mdestReg)
    begin
        fwda <= 2'b11;
    end
    else
    begin
        fwda <= 2'b00;
    end
end
*/
endmodule
