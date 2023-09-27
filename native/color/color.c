#include "stdio.h"
#include "color.h"

void test(struct Color color)
{
  printf("Color: %hhu, %hhu, %hhu, %hhu\n", color.r, color.g, color.b, color.a);
}
