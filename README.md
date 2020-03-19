# ASMBOTS
Source code for the original ASMBOTs. I released a few versions of ASMBOTs for Android. The source code here is the Soccer Bot version.

Some pirate sites still have the APK. I did not keep my playstore going, so it is not availble there.

Highlights of this code is implementation of virtual CPU's with assembly language interface.

Sound, images, and help files are included. Orginal development environment was eclipse (for Android).


Original Copy----------------------------------------------------
The Story of asmBots(tm)
Citizens of the Galaxy! The end of War and poverty is upon us. The Prodigal Devourers are extinct and the Shared Blessings of the galaxy are once again, ours.

With the end of the Great Abstracted War, the Union of Empathy and Quintessence designated an enormous amount of military robots as surplus. The Union authorized demilitarization and re-utilization of many robots and other surplus equipment.

The Coalition for Expanded Knowledge (an official Public Trust recognized by the Union), constructed a Robot Proving Ground on Imp (a large satellite of the planet Fomalhaut b).

All magnanimous Citizens of the Galaxy are welcome to use the facilities on Imp.


Central to each robot is a floating point Central Processing Unit (CPU) and Function Coprocessor (FCP). The FCP has been optimized for Arena Soccer.

The Central Processing Unit executes the commands it is given. You program the CPU with commands to implement your strategies.

The Function Co-Processor is a pre-programed processor that handles the details of controlling a robot or accessing some of the other features of the robot.

The CPU recognizes commands that interact with the FCP. In some cases, the FCP monitors the CPU and automatically responds as needed - such as moving the robot. In other cases, the FPU automatically updates the CPU with current status - such as the current position of the ball.

The CPU consists of a set of REGISTERS that contain numbers. The CPU recognizes a set of commands that operate on the REGISTERS (or the numbers they contain).

The Function Co-Processor is a pre-programed processor that handles the details of controlling a robot or accessing some of the other features of the robot.

The FCP monitors certain REGISTERS and automatically responds based on the value in the register. The FPU automatically updates certain registers as well. The registers updated by the FCP are inputs. The commands given to the CPU will generate output that causes the robot to respond by running or kicking a goal.

An example of a REGISTER being read by the FCP is the directional register. The FCP constantly reads the direction register and instructs the various sub-processors that cause the robot's servos and hydraulics to run in the specified direction.

An example of the FCP updating registers is the robot's position. The FCP updates the position registers with the robots current position obtained from the navigation sub-processor.

By reading the input registers, and setting the output registers, you can implement custom strategies.

When the CODE button is clicked, the current Debug Bot is opened in the code view window. When in code view, the current line of code is highlighted. Clicking on the STEP button, will cause the robot to run the highlighted code. The mainframe will update the game view.

While viewing code, you can select a line by long-clicking it. Long-clicking a line will open the code options. To edit the line of code, select EDIT from the code options.

Thankfully, when editing code, you do not need to type the commands or registers. The entry screen provides drop-downs for the commands and registers.

If you need to enter literal numbers or a label, use your device's soft or hard keyboard.

