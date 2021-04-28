/* TODO: Task (b) Please fill in the following lines, then remove this line.
 *
 * author(s):   Lukas Ingold
                20-123-998
 * modified:    2021-04-14
 *
 */

#include <stdlib.h>
#include <stdio.h>
#include "memory.h"
#include "mips.h"
#include "compiler.h"

int main ( int argc, char** argv ) {
    /* TODO: Task (c) implement main */
    if(argc<3){
      printf("%s \n", "usage: compile expression filename");
    }

    else{
      printf( "%s" "%s\n", "Input: ", argv[1] );
      verbose = TRUE;
      printf("%s", "Postfix:" );
      compiler(argv[1], argv[2]);
      verbose = FALSE;
      printf("\n%s" "%s\n", "MIPS binary saved to ", argv[2]);
    }
    return EXIT_SUCCESS;
}
