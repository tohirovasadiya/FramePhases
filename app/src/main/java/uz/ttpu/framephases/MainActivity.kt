package uz.ttpu.framephases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.ttpu.framephases.ui.theme.FramePhasesTheme

enum class DemoScreen(val title: String) {
    Q4_LECTURE_TREE("Q4: LectureTree"),
    Q5_PADDING("Q5: PaddingDemo"),
    Q6_OFFSET_LAYOUT("Q6: Offset (Layout Read)"),
    Q6_OFFSET_COMP("Q6: Offset (Comp Read)"),
    Q7_COLOR_DRAW("Q7: Color (Draw Read)"),
    Q7_COLOR_BOX("Q7: Color (Comp Read)"),
    Q9_PARALLAX_SLOW("Q9: Parallax Slow"),
    Q9_PARALLAX_FAST("Q9: Parallax Fast"),
    Q10_SCROLL_NAIVE("Q10: ScrollToTop Naive"),
    Q10_SCROLL_DERIVED("Q10: ScrollToTop Derived"),
    Q11_SKIP_VALUE("Q11: Skip (Value)"),
    Q11_SKIP_LAMBDA("Q11: Skip (Lambda)"),
    Q13_CYCLIC_LOOP("Q13: Cyclic Loop"),
    Q13_CYCLIC_FIXED("Q13: Cyclic Fixed"),
    Q14_STACK_BELOW("Q14: StackBelow Custom Layout"),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FramePhasesTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentDemo by remember { mutableStateOf(DemoScreen.Q4_LECTURE_TREE) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Compose Phases Demos (Filter Tag: Phases/Parallax/Derived/Skip/Cycle/StackBelow)",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DemoScreen.entries.forEach { screen ->
                    FilterChip(
                        selected = (currentDemo == screen),
                        onClick = { currentDemo = screen },
                        label = { Text(screen.title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                when (currentDemo) {
                    DemoScreen.Q4_LECTURE_TREE -> LectureTree()
                    DemoScreen.Q5_PADDING -> PaddingDemo()
                    DemoScreen.Q6_OFFSET_LAYOUT -> OffsetDemo()
                    DemoScreen.Q6_OFFSET_COMP -> OffsetDemoExperiment()
                    DemoScreen.Q7_COLOR_DRAW -> ColorDemo()
                    DemoScreen.Q7_COLOR_BOX -> ColorDemoBox()
                    DemoScreen.Q9_PARALLAX_SLOW -> ParallaxSlow()
                    DemoScreen.Q9_PARALLAX_FAST -> ParallaxFast()
                    DemoScreen.Q10_SCROLL_NAIVE -> ScrollToTopNaive()
                    DemoScreen.Q10_SCROLL_DERIVED -> ScrollToTopDerived()
                    DemoScreen.Q11_SKIP_VALUE -> Parent()
                    DemoScreen.Q11_SKIP_LAMBDA -> ParentDeferred()
                    DemoScreen.Q13_CYCLIC_LOOP -> CyclicDemo()
                    DemoScreen.Q13_CYCLIC_FIXED -> CyclicFixed()
                    DemoScreen.Q14_STACK_BELOW -> StackBelowDemo()
                }
            }
        }
    }
}
