`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 11/07/2022 06:32:31 PM
// Design Name: 
// Module Name: register_file
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

module register_file(
    input wire clk,
    input wire [4:0] rs,
    input wire [4:0] rt,
    input wire [4:0] wdestReg,
    input wire [31:0] wbData,
    input wire wwreg,
    
    output reg[31:0] qa,
    output reg[31:0] qb
    );
    
reg [31:0] registers [0:31]; 
integer i; // index of registers 
initial 
    begin
        registers[0] = 32'h00000000;
        registers[1] = 32'hA00000AA;
        registers[2] = 32'h10000011;
        registers[3] = 32'h20000022;
        registers[4] = 32'h30000033;
        registers[5] = 32'h40000044;
        registers[6] = 32'h50000055;
        registers[7] = 32'h60000066;
        registers[8] = 32'h70000077;
        registers[9] = 32'h80000088;
        registers[10] = 32'h90000099;
    
        // initilize each array in a 32 bit to 0; 
        for(i = 11; i < 32; i = i + 1) 
        begin
        // sets that register to 0 
        registers[i] = 32'b0;
        end
    end
always @ (negedge clk)
begin
    if(wwreg == 1) begin
        registers[wdestReg] = wbData;
        end 

end
        
    
always @(*)
begin
    qa = registers[rs]; 
    qb = registers[rt];
end


endmodule

   /*
reg [31:0] registers [0:31]; //creates a 2d array with registers with 32 bit length
integer register2;
initial 
begin 
    for(register2 = 0; register2< 32; register2 = register2 + 1) //loop that set the arrays to 0
    begin
    registers[register2] = 32'd0;
    end
    
    registers[0] = 32'h00000000;
    registers[1] = 32'hA00000AA;
    registers[2] = 32'h10000011;
    registers[3] = 32'h20000022;
    registers[4] = 32'h30000033;
    registers[5] = 32'h40000044;
    registers[6] = 32'h50000055;
    registers[7] = 32'h60000066;
    registers[8] = 32'h70000077;
    registers[9] = 32'h80000088;
    registers[10] = 32'h90000099;
   
end
always @(*)
begin
    qa <= registers[rs]; 
    qb <= registers[rt];
end
always @(negedge clk)
begin
    if (wwreg == 1)
    begin
        registers[wdestReg] = wbData;  
    end
end
endmodule
*/
